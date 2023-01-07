import { Badge, Button, Descriptions, Empty, Form, Input, message, Typography, DatePicker } from 'antd';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { Link, useParams } from 'react-router-dom';
import { ActionsCard } from '../../components/actionsCard/ActionsCard';
import { useAppUser } from '../../hooks/useAppUser';
import { useAddAppointmentMutation } from '../../store/api/appointment/appointmentApi';
import { AppointmentAddRequest } from '../../store/api/appointment/types';
import { useGetFlatQuery } from '../../store/api/flat/flatApi';
import dayjs from 'dayjs';

import styles from './FlatPage.module.scss'
import { RangePickerProps } from 'antd/es/date-picker';
const { Text } = Typography;

interface AddAppointmentFormValues {
  appointmentName: string;
  appointmentDate: Date;
}

const disabledDateTime = () => ({
  disabledHours: () => [0, 1, 2, 3, 4, 5, 6, 7, 18, 19, 20, 21, 22, 23, 24]
});

const disabledDate: RangePickerProps['disabledDate'] = (current) => {
  return current && current < dayjs().endOf('day');
};

export const FlatPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const { data } = useGetFlatQuery(Number(id));
  const [addAppointment] = useAddAppointmentMutation();
  const [messageApi, contextHolder] = message.useMessage();
  const appUser = useAppUser();
  const [form] = Form.useForm();

  const handleAddAppointment = async (values: AddAppointmentFormValues) => {
    const { appointmentName, appointmentDate } = values;

    if (appUser._type !== "AUTHENTICATED") {
      throw new Error("User is not authenticated");
    }

    const request: AppointmentAddRequest = {
      appointmentDto: {
        appointmentName,
        appointmentDate: dayjs(appointmentDate).format('YYYY-MM-DD HH:mm'),
        flatId: Number(id)
      }
    }

    addAppointment({ body: request, token: appUser.token }).then(() => {
      messageApi.loading("Przetwarzanie spotkania ...");
    }).catch(() => {
      console.log('catch');
      messageApi.error("Dodawanie nie powiodło się :(")
    }).finally(() => {
      console.log("finally")
      messageApi.destroy();
      messageApi.success("Spotkanie dodane");
      form.resetFields();
    })
  }

  if (!data?.flatDto) {
    return <Empty />
  }

  const { flatName, address, area, constructionYear, floor, isActive, numberOfRooms, priceOfFlat, pricePerSquareMeter } = data.flatDto;
  const { country, city, district, street, postal_code } = address;

  return (
    <article>
      {contextHolder}
      <Link to="/flats">{`<- Wróc do listy mieszkań`}</Link>
      <Title className={styles.title}>{flatName}</Title>
      <div className={styles.content}>
        <Descriptions title="Dane mieszkania" bordered column={{ xs: 1, sm: 1, md: 2, lg: 3 }}>
          <Descriptions.Item label="Nazwa">{flatName}</Descriptions.Item>
          <Descriptions.Item label="Rok wybudowania">{constructionYear}</Descriptions.Item>
          <Descriptions.Item label="Piętro">{floor}</Descriptions.Item>
          <Descriptions.Item label="Ilość pokoi">{numberOfRooms}</Descriptions.Item>
          <Descriptions.Item label="Powierzchnia">
            {area}m2
          </Descriptions.Item>
          <Descriptions.Item label="Status">
            <Badge status={isActive ? "success" : "error"} text={isActive ? "Dostępne" : "Niedostępne"} />
          </Descriptions.Item>
          <Descriptions.Item label="Cena za m^2">{pricePerSquareMeter}zł</Descriptions.Item>
          <Descriptions.Item label="Cena" span={2}>{priceOfFlat}zł</Descriptions.Item>
          <Descriptions.Item label="Adres">
            Ulica: {street}
            <br />
            Dzielnica: {district}
            <br />
            Miasto: {city}
            <br />
            Kod pocztowy: {postal_code}
            <br />
            Kraj: {country}
          </Descriptions.Item>
        </Descriptions>
        {appUser._type === "AUTHENTICATED" && appUser.isCustomer && (
          <div className={styles.actionWrapper}>
            <ActionsCard>
              <Title level={2} className={styles.addTitle}>
                Jesteś zainteresowany?
              </Title>
              <Text>Zarezerwuj spotkanie z naszym agentem a opowiemy Ci o wszystkich szczegółach tego mieszkania!</Text>
              <Form
                className={styles.addForm}
                layout="vertical"
                form={form}
                onFinish={handleAddAppointment}
                scrollToFirstError
              >
                <Form.Item label="Nazwa spotkania" name="appointmentName" rules={[
                  {
                    required: true,
                    message: 'Nazwa spotkania jest wymagana',
                  },
                ]}>
                  <Input placeholder="Wizja lokalna" />
                </Form.Item>
                <Form.Item label="Data spotkania" name="appointmentDate" rules={[
                  {
                    required: true,
                    message: 'Data spotkania jest wymagana',
                  },
                ]}>
                  <DatePicker
                    className={styles.datePicker}
                    format='YYYY-MM-DD HH:mm'
                    disabledDate={disabledDate}
                    disabledTime={disabledDateTime}
                    showTime={{ defaultValue: dayjs('08:00', 'HH:mm') }}
                  />
                </Form.Item>
                <Button
                  type="primary"
                  htmlType="submit"
                  className={styles.addAppointmentButton}
                >
                  Zamów spotkanie
                </Button>
              </Form>
            </ActionsCard>
          </div>
        )}
      </div>
    </article>
  )
}

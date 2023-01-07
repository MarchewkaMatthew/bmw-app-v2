import { Badge, Button, Descriptions, Empty, message } from 'antd';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { Link, useParams } from 'react-router-dom';
import { useAppUser } from '../../hooks/useAppUser';
import { useAddAppointmentMutation } from '../../store/api/appointment/appointmentApi';
import { AppointmentAddRequest } from '../../store/api/appointment/types';
import { useGetFlatQuery } from '../../store/api/flat/flatApi';

import styles from './FlatPage.module.scss'

export const FlatPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const { data } = useGetFlatQuery(Number(id));
  const [addAppointment] = useAddAppointmentMutation();
  const [messageApi, contextHolder] = message.useMessage();
  const appUser = useAppUser();

  const handleAddAppointment = async () => {
    console.log("zamów spotkanie");

    if (appUser._type !== "AUTHENTICATED") {
      throw new Error("User is not authenticated");
    }

    const request: AppointmentAddRequest = {
      appointmentDto: {
        appointmentName: "Fajne spotkanie",
        appointmentDate: "2023-11-23 00:00",
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
        <Descriptions title="Dane mieszkania" bordered>
          <Descriptions.Item label="Nazwa">{flatName}</Descriptions.Item>
          <Descriptions.Item label="Rok wybudowania">{constructionYear}</Descriptions.Item>
          <Descriptions.Item label="Piętro">{floor}</Descriptions.Item>
          <Descriptions.Item label="Ilość pokoi">{numberOfRooms}</Descriptions.Item>
          <Descriptions.Item label="Powierzchnia">
            {area}
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
        <div className={styles.actionWrapper}>
          <Button type="primary" onClick={handleAddAppointment}>Zamów spotkanie</Button>
        </div>
      </div>
    </article>
  )
}

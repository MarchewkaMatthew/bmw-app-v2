import {
  DatePicker,
  Divider,
  Form,
  Input,
  InputNumber,
  Modal,
  Switch,
} from "antd";
import React from "react";
import { AddressDto, FlatDto } from "../../store/api/flat/types";
import dayjs from "dayjs";

import styles from "./FlatModal.module.scss";

export type ModalFlat = Omit<FlatDto, "id" | "address"> &
  Partial<Pick<FlatDto, "id">> & { address: Omit<AddressDto, "id"> };

interface FlatModalProps {
  flat?: FlatDto;
  mode: "EDIT" | "ADD";
  // MODAL
  open: boolean;
  onOk: (flat: ModalFlat) => void;
  onCancel: () => void;
  confirmLoading: boolean;
}

export const FlatModal: React.FC<FlatModalProps> = (props) => {
  const { flat, mode, open, onOk, onCancel, confirmLoading } = props;
  const [form] = Form.useForm();

  const handleOk = () => {
    form.submit();
  };

  const handleCancel = () => {
    form.resetFields();
    onCancel();
  };

  const handleFormSubmit = (values: any) => {
    const formattedValues = {
      ...values,
      constructionYear: dayjs(values.constructionYear).year().toString(),
      address: {
        ...values.address,
        country: "Polska",
        location: null,
      },
    };

    onOk(formattedValues as ModalFlat);
  };

  return (
    <Modal
      title={
        mode === "ADD"
          ? "Dodawanie nowej nieruchomości"
          : `Edytowanie nieruchomości ${flat?.flatName}`
      }
      open={open}
      onOk={handleOk}
      confirmLoading={confirmLoading}
      onCancel={handleCancel}
    >
      <Form
        form={form}
        onFinish={handleFormSubmit}
        scrollToFirstError
        layout="vertical"
        initialValues={{
          ...flat,
          constructionYear: dayjs().year(
            flat?.constructionYear ? Number(flat.constructionYear) : 2023
          ),
          isActive: flat?.isActive || true,
        }}
      >
        <Form.Item
          name="flatName"
          label="Nazwa nieruchomości"
          rules={[{ required: true }]}
        >
          <Input defaultValue={flat?.flatName} />
        </Form.Item>
        <Form.Item
          name="area"
          label="Powierzchnia"
          rules={[{ required: true, type: "number", min: 10, max: 1000 }]}
        >
          <InputNumber defaultValue={flat?.area} className={styles.control} />
        </Form.Item>
        <Form.Item
          name="pricePerSquareMeter"
          label="Cena za m^2"
          rules={[{ required: true, type: "number", min: 10, max: 30000 }]}
        >
          <InputNumber
            defaultValue={flat?.pricePerSquareMeter}
            className={styles.control}
            addonAfter="zł"
          />
        </Form.Item>
        <Form.Item
          name="numberOfRooms"
          label="Ilość pokoi"
          rules={[{ required: true, type: "number", min: 0, max: 100 }]}
        >
          <InputNumber
            defaultValue={flat?.numberOfRooms}
            className={styles.control}
          />
        </Form.Item>
        <Form.Item
          name="floor"
          label="Piętro"
          rules={[{ required: true, type: "number", min: 0, max: 100 }]}
        >
          <InputNumber defaultValue={flat?.floor} className={styles.control} />
        </Form.Item>
        <Form.Item
          name="constructionYear"
          label="Rok budowy"
          rules={[{ required: true, type: "date" }]}
        >
          <DatePicker picker="year" className={styles.control} />
        </Form.Item>
        <Form.Item
          name="isActive"
          label="Czy jest dostępne?"
          rules={[{ required: true, type: "boolean" }]}
          valuePropName="checked"
        >
          <Switch />
        </Form.Item>
        <Divider>Adres</Divider>
        <Form.Item
          name={["address", "street"]}
          label="Ulica"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name={["address", "city"]}
          label="Miasto"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name={["address", "postal_code"]}
          label="Kod pocztowy"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name={["address", "district"]}
          label="Województwo"
          rules={[{ required: true }]}
        >
          <Input />
        </Form.Item>
      </Form>
    </Modal>
  );
};

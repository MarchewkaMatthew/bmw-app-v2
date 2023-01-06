import { Button, Col, Form, Input, message, Row } from 'antd';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { v4 as uuid } from 'uuid';

import styles from "./ContactPage.module.scss"
import { MessageDto } from '../../store/api/contact/types';
import { useAddMessageMutation } from '../../store/api/contact/contactApi';

type ContactFormValues = Omit<MessageDto, "id">

export const ContactPage: React.FC = () => {
  const [form] = Form.useForm();
  const [messageApi, contextHolder] = message.useMessage();
  const [addPost] = useAddMessageMutation()

  const onFinish = async (values: ContactFormValues) => {
    const id = uuid();

    addPost({ messageDto: { id, ...values } }).then(() => {
      messageApi.loading("Wiadomość wysyła się");
    }).catch(() => {
      console.log('catch');
      messageApi.error("Wysyłanie nie powiodło się :(")
    }).finally(() => {
      console.log("finally")
      messageApi.destroy();
      messageApi.success("Wiadomość wysłana");
      form.resetFields();
    })
  };

  return (
    <article>
      {contextHolder}
      <Title className={styles.title}>Zostaw nam swoje dane, a na pewno się odezwiemy!</Title>
      <Row gutter={24} className={styles.content}>
        <Col xs={24} md={16} xl={12}>
          <Form
            form={form}
            name="contact"
            onFinish={onFinish}
            scrollToFirstError
            className={styles.form}
            layout="vertical"
          >
            <Form.Item
              name="firstname"
              label="Imie"
              rules={[
                {
                  required: true,
                  message: 'Imie jest wymagane',
                },
              ]}
              className={styles.formItem}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="lastname"
              label="Nazwisko"
              className={styles.formItem}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="email"
              label="E-mail"
              rules={[
                {
                  type: 'email',
                  message: 'E-mail jest niepoprawny',
                },
                {
                  required: true,
                  message: 'E-mail jest wymagany',
                },
              ]}
              className={styles.formItem}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="phoneNumber"
              label="Numer telefonu"
              rules={[
                {
                  // TODO: Zrozumieć czemu ten regex nie działa
                  // pattern: new RegExp('(?<!\w)(\(?(\+|00)?48\)?)?[ -]?\d{3}[ -]?\d{3}[ -]?\d{3}(?!\w)'),
                  message: 'Podany numer nie jest właściwy',
                },
              ]}
              className={styles.formItem}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="additionalInformation"
              label="Dodatkowe informacje"
              className={styles.formItem}
            >
              <Input.TextArea rows={4} />
            </Form.Item>
            <Button type="primary" className={styles.formSubmit} htmlType="submit">Wyślij</Button>
          </Form>
        </Col>
      </Row>
    </article>
  )
}

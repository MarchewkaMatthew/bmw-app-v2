import { Badge, Descriptions, Empty } from 'antd';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { useParams } from 'react-router-dom';
import { useGetFlatQuery } from '../../store/api/flat/flatApi';

import styles from './FlatsPage.module.scss'

export const FlatPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const { data } = useGetFlatQuery(Number(id));

  if (!data?.flatDto) {
    return <Empty />
  }

  const { flatName, address, area, constructionYear, floor, isActive, numberOfRooms, priceOfFlat, pricePerSquareMeter } = data.flatDto;
  const { country, city, district, street, postal_code } = address;

  return (
    <article>
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
      </div>
    </article>
  )
}

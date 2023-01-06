import { List, Image, Row, Col, Statistic, Tag, Space, Typography } from 'antd';
import React from 'react';
import { FlatDto } from '../../store/api/flat/types';
import unknownItemImage from '../../assets/images/unknown_item.png';

import styles from "./FlatListItem.module.scss";
import { Link } from 'react-router-dom';

const { Text } = Typography;

interface FlatListItemProps {
  flat: FlatDto;
}

export const FlatListItem: React.FC<FlatListItemProps> = (props) => {
  const { flat } = props
  const { id, flatName, address, priceOfFlat, pricePerSquareMeter, numberOfRooms, floor, area, constructionYear } = flat;
  const { country, city, district, street } = address;

  return (
    <Link to={`/flats/${id}`}>
      <List.Item
        key={id}
        extra={
          <Image
            src={unknownItemImage}
            alt=" "
            width={160}
            height={90}
            className={styles.image}
          />
        }
      >
        <List.Item.Meta
          title={flatName}
          description={
            <>
              <Row gutter={16}>
                <Space direction="horizontal" className={styles.address}>
                  <Text>Ulica: {street}</Text>
                  <Text>Dzielnica: {district}</Text>
                  <Text>Miasto: {city}</Text>
                  <Text>Kraj: {country}</Text>
                </Space>
              </Row>
              <Row gutter={16}>
                <Col xs={24} md={12} xl={8}>
                  <Statistic title="Powierzchnia" value={`${area}m^2`} />
                </Col>
                <Col xs={24} md={12} xl={8}>
                  <Statistic title="Cena za m^2" value={`${pricePerSquareMeter}zł`} />
                </Col>
                <Col xs={24} md={12} xl={8}>
                  <Statistic title="Cena" value={`${priceOfFlat}zł`} />
                </Col>
              </Row>
              <Row gutter={16} className={styles.infoRow}>
                <Tag>Ilość pokoi: {numberOfRooms}</Tag>
                <Tag>Piętro: {floor}</Tag>
                <Tag>Rok wybudowania:{constructionYear}</Tag>
              </Row>
            </>
          }
        />
      </List.Item>
    </Link>
  )
}

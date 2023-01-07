import { EditOutlined, PlusCircleOutlined, PoweroffOutlined } from '@ant-design/icons';
import { Badge, Button, Card, List, Tag, Tooltip, Typography } from 'antd';
import Meta from 'antd/es/card/Meta';
import React from 'react';
import { Link } from 'react-router-dom';
import { FlatDto } from '../../store/api/flat/types';
import styles from "./FlatCardItem.module.scss";

const { Text } = Typography;


interface FlatCardItemProps {
  flat: FlatDto;
}

export const FlatCardItem: React.FC<FlatCardItemProps> = (props) => {
  const { flat } = props
  const { id, flatName, address, priceOfFlat, pricePerSquareMeter, numberOfRooms, floor, area, constructionYear, isActive } = flat;
  const { country, city, district, street } = address;

  const actions = [
    <Tooltip title="Edytuj">
      <Button
        type="text"
        onClick={() => {
          console.log("EDIT")
        }}
      >
        <EditOutlined key="edit" />
      </Button>
    </Tooltip>,
    <Tooltip title={isActive ? "Dezaktywuj" : "Aktywuj"}>
      <Button
        type="text"
        onClick={() => {
          if (isActive) {
            console.log("DEACTIVATE")
          } else {
            console.log("ACTIVATE")
          }
        }}
      >
        {isActive ? <PoweroffOutlined key="deactivate" /> : <PlusCircleOutlined key="activate" />}
      </Button>
    </Tooltip>
  ]

  return (
    <List.Item key={id} className={styles.container}>
      <Badge.Ribbon text={isActive ? "Dostępne" : "Niedostępne"} color={isActive ? "green" : "red"}>
        <Card actions={actions}>
          <Meta
            title={<Link to={`/flats/${id}`} target="_blank">{flatName}</Link>}
            description={
              <>
                <Text>ulica: {street}, miasto: {city}, województwo: {district}, kraj: {country}</Text>
                <ul>
                  <li>Powierzchnia: {area}m2</li>
                  <li>Cena za m2: {pricePerSquareMeter}zł</li>
                  <li>Cena: {priceOfFlat}zł</li>
                </ul>
                <div className={styles.infoRow}>
                  <Tag>Ilość pokoi: {numberOfRooms}</Tag>
                  <Tag>Piętro: {floor}</Tag>
                  <Tag>Rok wybudowania:{constructionYear}</Tag>
                </div>
              </>
            }
          />
        </Card>
      </Badge.Ribbon>
    </List.Item>
  )
}

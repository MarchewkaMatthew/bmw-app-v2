import { PlusOutlined } from '@ant-design/icons';
import { Alert, Button, List, Typography } from 'antd';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { useAppUser } from '../../hooks/useAppUser';
import { useGetFlatsQuery } from '../../store/api/flat/flatApi';
import { FlatCardItem } from '../flatCardItem/FlatCardItem';

import styles from "./AgentPanel.module.scss"
const { Text } = Typography;

// // AGENT PANEL

// CZEŚĆ AGENT!
//   LISTA WIADOMOŚCI (TODO: DODAĆ MOŻLIWOŚĆ EDYCJI STATUSU WIADOMOŚCI NA ODPOWIEDZIANA)
//   LISTA SPOTKAŃ + MOLIWOŚĆ ZMIANY SPOTKANIA (TODO: DODAĆ FLAGĘ isCanceled), USUWANIE SPOTKANIA

export const AgentPanel: React.FC = () => {
  const appUser = useAppUser();
  const { data, isLoading, isError, refetch } = useGetFlatsQuery("");


  if (appUser._type !== "AUTHENTICATED") {
    return null;
  }

  const { userName } = appUser;

  return (
    <div>
      <Title>{`Cześć ${userName}!`}</Title>
      <Text>Twoja rola to "AGENT"</Text>
      <div className={styles.flatsSection}>
        <div className={styles.sectionTitleContainer}>
          <Title level={2} className={styles.sectionTitle}>Nieruchomości</Title>
          <Button icon={<PlusOutlined />}>Dodaj nieruchomość</Button>
        </div>
        {isError ? (
          <Alert
            message="Wczytanie mieszkań nie powiodło się"
            description="Spróbuj później"
            type="error"
            showIcon
          />
        ) : (
          <List
            dataSource={data?.flatDtoList}
            grid={{
              gutter: 16,
              xs: 1,
              sm: 1,
              md: 1,
              lg: 2,
              xl: 3,
              xxl: 4,
            }}
            loading={isLoading}
            renderItem={flat => <FlatCardItem flat={flat} onFlatChange={refetch} />}
          />
        )}
      </div>
    </div>
  )
}

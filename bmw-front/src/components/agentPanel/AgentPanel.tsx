import { Typography } from 'antd';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { useAppUser } from '../../hooks/useAppUser';

import styles from "./AgentPanel.module.scss"
const { Text } = Typography;

// // AGENT PANEL

// CZEŚĆ AGENT!
//   LISTA WIADOMOŚCI (TODO: DODAĆ MOŻLIWOŚĆ EDYCJI STATUSU WIADOMOŚCI NA ODPOWIEDZIANA)
//   LISTA NIERUCHOMOŚCI + MOŻLIWOŚĆ DODANIA NOWEJ, EDYCJI STARYCH
//   LISTA SPOTKAŃ + MOLIWOŚĆ ZMIANY SPOTKANIA (TODO: DODAĆ FLAGĘ isCanceled), USUWANIE SPOTKANIA

export const AgentPanel: React.FC = () => {
  const appUser = useAppUser();


  if (appUser._type !== "AUTHENTICATED") {
    return null;
  }

  const { userName } = appUser;

  return (
    <div className={styles.container}>
      <Title>{`Cześć ${userName}!`}</Title>
      <Text>Twoja rola to "AGENT"</Text>
    </div>
  )
}

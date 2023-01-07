import Title from 'antd/es/typography/Title';
import { Typography } from 'antd';
import React from 'react';
import { useAppUser } from '../../hooks/useAppUser';

import styles from "./CustomerPanel.module.scss";
const { Text } = Typography;


// // KLIENT PANEL

// CZEŚĆ KLIENT!
//   LISTA MOICH SPOTKAŃ + MOŻLIWOŚĆ USUNIĘCIA ICH (TODO: KLIENT NIE POWINIEN MIEĆ MOŻLIWOŚCI EDYCJI CAŁEGO, TYLKO EW. STATUS I DATA)
//   TODO: DODAĆ FUNKCJONALNOŚĆ DODAWANIA MIESZKANIA DO ULUBIONYCH -> LISTA MIESZKAN NA PANELU
//   TODO: CZY POWINNISMY JAKOS OBSŁUZYC PROCES KUPOWANIA?

export const CustomerPanel: React.FC = () => {
  const appUser = useAppUser();


  if (appUser._type !== "AUTHENTICATED") {
    return null;
  }

  const { userName } = appUser;

  return (
    <div className={styles.container}>
      <Title>{`Cześć ${userName}!`}</Title>
      <Text>Twoja rola to "CUSTOMER"</Text>
    </div>
  )
}

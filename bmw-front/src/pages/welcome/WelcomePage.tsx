import { Button, Spin } from 'antd';
import Title from 'antd/es/typography/Title';
import { LoadingOutlined } from '@ant-design/icons';
import React from 'react';

import styles from "./WelcomePage.module.scss";
import { useAppUser } from '../../hooks/useAppUser';

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

export const WelcomePage: React.FC = () => {
  const appUser = useAppUser();

  if (appUser._type === "UNINITIALIZED") {
    return (
      <div className={styles.spinnerContainer}>
        <Spin indicator={antIcon} />
      </div>
    )
  }

  /*

  TO DO: Dodać user info do appointment

  // NOT_AUTHENTICATED PANEL

  ZALOGUJ SIE
  ZAREJESTRUJ SIE

  // AGENT PANEL

  CZEŚĆ AGENT!
    LISTA WIADOMOŚCI (TODO: DODAĆ MOŻLIWOŚĆ EDYCJI STATUSU WIADOMOŚCI NA ODPOWIEDZIANA)
    LISTA NIERUCHOMOŚCI + MOŻLIWOŚĆ DODANIA NOWEJ, EDYCJI STARYCH
    LISTA SPOTKAŃ + MOLIWOŚĆ ZMIANY SPOTKANIA (TODO: DODAĆ FLAGĘ isCanceled), USUWANIE SPOTKANIA

  // KLIENT PANEL

  CZEŚĆ KLIENT!
    LISTA MOICH SPOTKAŃ + MOŻLIWOŚĆ USUNIĘCIA ICH (TODO: KLIENT NIE POWINIEN MIEĆ MOŻLIWOŚCI EDYCJI CAŁEGO, TYLKO EW. STATUS I DATA)
    TODO: DODAĆ FUNKCJONALNOŚĆ DODAWANIA MIESZKANIA DO ULUBIONYCH -> LISTA MIESZKAN NA PANELU
    TODO: CZY POWINNISMY JAKOS OBSŁUZYC PROCES KUPOWANIA?
  */

  return (
    <article>
      {appUser._type === "AUTHENTICATED" && <Title className={styles.title}>Cześć {appUser.userName}!</Title>}
      <div className={styles.content}>
        {appUser._type === "NOT_AUTHENTICATED" &&
          <Button onClick={appUser.register}>Zarejestruj się</Button>
        }
      </div>
    </article>
  )
}

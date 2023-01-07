import { Button, Divider, Typography, } from 'antd';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { useAppUser } from '../../hooks/useAppUser';
import { ActionsCard } from '../actionsCard/ActionsCard';

import styles from "./NotAuthenticatedPanel.module.scss";
const { Text } = Typography;

export const NotAuthenticatedPanel: React.FC = () => {
  const appUser = useAppUser();

  if (appUser._type !== "NOT_AUTHENTICATED") {
    return null;
  }

  const { login, register } = appUser;

  return (
    <div className={styles.wrapper}>
      <ActionsCard>
        <Title level={2} className={styles.title}>
          Szukasz nieruchomości do kupienia? Dobrze trafiłeś!
        </Title>
        <Text>Zaloguj się na juz istniejące konto.</Text>
        <Button onClick={login} type="primary" className={styles.button}>Zaloguj się</Button>
        <Divider>lub</Divider>
        <Text>Stwórz nowe konto w naszym serwisie.</Text>
        <Button onClick={register} className={styles.button}>Zarejestruj się</Button>
      </ActionsCard>
    </div>
  )
}

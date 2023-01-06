import React from 'react';
import { Button, Layout } from 'antd';
import { Header, Content, Footer } from 'antd/es/layout/layout';
import styles from "./Root.module.scss";
import { Wrapper } from '../components/wrapper/Wrapper';
import { Logo } from '../components/logo/Logo';
import { Link, Outlet } from 'react-router-dom';
import { useKeycloak } from '@react-keycloak/web';

export const Root: React.FC = () => {
  const { keycloak, initialized } = useKeycloak()

  console.log(keycloak, initialized);
  
  return (
    <article>
      <Layout className={styles.layout} >
        <Header className={styles.header}>
          <Wrapper className={styles.headerWrapper}>
            <Link to="/">
              <Logo />
            </Link>
            <div className={styles.menu}>
              <Link to="/flats">Nieruchomości</Link>
              <Link to="/contact">Kontakt</Link>
              {keycloak.authenticated ?
                <Button onClick={() => keycloak.logout()}>Wyloguj się</Button> :
                <Button onClick={() => keycloak.login()}>Zaloguj się</Button>
              }
            </div>
          </Wrapper>
        </Header>
        <Content>
          <Wrapper>
            <div className={styles.contentWrapper}>
              <Outlet />
            </div>
          </Wrapper>
        </Content>
        <Footer className={styles.footer}>BMW app ©2023 Created by Mateusz Marchewka, Patryk Małek and Jakub Kolasiak</Footer>
      </Layout>
    </article>
  )
}

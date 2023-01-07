import React from 'react';
import { Button, Layout } from 'antd';
import { Header, Content, Footer } from 'antd/es/layout/layout';
import styles from "./Root.module.scss";
import { Wrapper } from '../components/wrapper/Wrapper';
import { Logo } from '../components/logo/Logo';
import { Link, Outlet } from 'react-router-dom';
import { useAppUser } from '../hooks/useAppUser';

export const Root: React.FC = () => {
  const appUser = useAppUser();

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
              {appUser._type === "AUTHENTICATED" && (
                <Button onClick={() => appUser.logout()}>Wyloguj się</Button>
              )}
              {appUser._type === "NOT_AUTHENTICATED" && (
                <Button onClick={() => appUser.login()}>Zaloguj się</Button>
              )}
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

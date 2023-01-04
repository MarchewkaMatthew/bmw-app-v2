import { Breadcrumb, Layout, Menu } from 'antd';
import { Header, Content, Footer } from 'antd/es/layout/layout';
import React from 'react';
import { Logo } from '../components/logo/Logo';
import { Wrapper } from '../components/wrapper/Wrapper';
import { Counter } from '../features/counter/Counter';

import styles from './App.module.scss';

export const App: React.FC = () => {
  return (
    <Layout className={styles.layout} >
      <Header className={styles.header}>
        <Wrapper className={styles.headerWrapper}>
          <Logo />
          <Menu
            theme="dark"
            mode="horizontal"
            className={styles.menu}
            defaultSelectedKeys={['nieruchomosci']}
            items={[
              { key: "nieruchomosci", label: "Nieruchomości"},
              { key: "moje-inwestycje", label: "Moje inwestycje"},
              { key: "klienci", label: "Klienci"},
              { key: "agenci", label: "Agenci"},
              { key: "formularz-kontaktowy", label: "Formularz kontaktowy"},
            ]}
          />
        </Wrapper>
      </Header>
      <Content>
        <Wrapper>
          <Breadcrumb style={{ margin: '16px 0' }}>
            <Breadcrumb.Item>Home</Breadcrumb.Item>
            <Breadcrumb.Item>List</Breadcrumb.Item>
            <Breadcrumb.Item>App</Breadcrumb.Item>
          </Breadcrumb>
          <div className={styles.contentWrapper}>
            <Counter />
          </div>
        </Wrapper>
      </Content>
      <Footer className={styles.footer}>BMW app ©2023 Created by Mateusz Marchewka, Patryk Małek and Jakub Kolasiak</Footer>
    </Layout>
  )
}

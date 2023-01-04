import { Breadcrumb, Layout } from 'antd';
import { Header, Content, Footer } from 'antd/es/layout/layout';
import Link from 'antd/es/typography/Link';
import React from 'react';
import { Logo } from '../components/logo/Logo';
import { Wrapper } from '../components/wrapper/Wrapper';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import styles from './App.module.scss';
import { FlatsPage } from '../pages/flat/FlatsPage';
import { ContactPage } from '../pages/contact/ContactPage';

export const App: React.FC = () => {
  return (
    <Router>
      <Layout className={styles.layout} >
        <Header className={styles.header}>
          <Wrapper className={styles.headerWrapper}>
            <Logo />
            {/* <Menu
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
            /> */}
            <div className={styles.menu}>
              menu
            </div>
          </Wrapper>
        </Header>
        <Content>
          <Wrapper>
            <Breadcrumb style={{ margin: '16px 0' }}>
              {/* <Breadcrumb.Item>Home</Breadcrumb.Item>
              <Breadcrumb.Item>List</Breadcrumb.Item>
              <Breadcrumb.Item>App</Breadcrumb.Item> */}
            </Breadcrumb>
            <div className={styles.contentWrapper}>
              <Routes>
                <Route path="/flats" element={<FlatsPage />} />
                <Route path="/contact" element={<ContactPage />} />
              </Routes>
            </div>
          </Wrapper>
        </Content>
        <Footer className={styles.footer}>BMW app ©2023 Created by Mateusz Marchewka, Patryk Małek and Jakub Kolasiak</Footer>
      </Layout>
    </Router>
  )
}

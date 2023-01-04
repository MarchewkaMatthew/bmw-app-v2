import { Breadcrumb, Layout, Menu } from 'antd';
import { Header, Content, Footer } from 'antd/es/layout/layout';
import React from 'react';
import { Wrapper } from '../components/wrapper/Wrapper';
import { Counter } from '../features/counter/Counter';

import styles from './App.module.scss';

export const App: React.FC = () => {
  return (
    <Layout className={styles.layout} >
      <Header className={styles.header}>
        <Wrapper>
          <div className="logo" />
          <Menu
            theme="dark"
            mode="horizontal"
            defaultSelectedKeys={['2']}
            items={new Array(15).fill(null).map((_, index) => {
              const key = index + 1;
              return {
                key,
                label: `nav ${key}`,
              };
            })}
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

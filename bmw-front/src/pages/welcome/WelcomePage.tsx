import { Spin } from 'antd';
import { LoadingOutlined } from '@ant-design/icons';
import React from 'react';

import styles from "./WelcomePage.module.scss";
import { useAppUser } from '../../hooks/useAppUser';
import { NotAuthenticatedPanel } from '../../components/notAuthenticatedPanel/NotAuthenticatedPanel';
import { AgentPanel } from '../../components/agentPanel/AgentPanel';
import { CustomerPanel } from '../../components/customerPanel/CustomerPanel';

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
    TO DO: Dodać liste appointment, na strone nieruchomości
  */

  const DashboardContent = () => {
    if (appUser._type === "NOT_AUTHENTICATED") {
      return <NotAuthenticatedPanel />
    }

    if (appUser._type !== "AUTHENTICATED") {
      throw new Error("Wrong navigation logic")
    }

    if (appUser.isAgent) {
      return <AgentPanel />
    }

    return <CustomerPanel />
  }

  return (
    <article>
      <DashboardContent />
    </article >
  )
}

import React from 'react';

import styles from "./ActionsCard.module.scss";

interface ActionsCardProps {
  children: React.ReactNode;
}

export const ActionsCard: React.FC<ActionsCardProps> = (props) => {
  const { children } = props
  return (
    <div className={styles.card}>
      {children}
    </div>
  )
}

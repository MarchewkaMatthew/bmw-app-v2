import React from 'react';

import styles from "./Wrapper.module.scss"

interface WrapperProps {
  children: React.ReactNode;
}

export const Wrapper: React.FC<WrapperProps> = (props) => {
  const { children } = props
  return <div className={styles.wrapper}>{children}</div>
}

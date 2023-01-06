import React from 'react';

import styles from "./Wrapper.module.scss"

interface WrapperProps {
  children: React.ReactNode;
  className?: string;
}

export const Wrapper: React.FC<WrapperProps> = (props) => {
  const { children, className } = props
  return <div className={`${styles.wrapper} ${className}`}>{children}</div>
}

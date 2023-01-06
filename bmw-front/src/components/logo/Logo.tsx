import React from 'react';

import styles from './Logo.module.scss';

export const Logo: React.FC = () => {
  return (
    <div className={styles.logo}>
      <p className={styles.name}>BMW</p>
      <span className={styles.slogan}>Będziesz miał wydatki</span>
    </div>
  )
}

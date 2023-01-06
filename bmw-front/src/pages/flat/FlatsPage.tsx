import { Button } from 'antd';
import Search from 'antd/es/input/Search';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { useGetFlatsQuery } from '../../store/api/flat/flatApi';

import styles from "./FlatsPage.module.scss"

export const FlatsPage: React.FC = () => {
  const { data, error, isLoading } = useGetFlatsQuery("")

  console.log(data, error, isLoading);

  const onSearch = (value: string) => console.log(value);

  return (
    <article>
      <Title className={styles.title}>Nieruchomości</Title>
      <div className={styles.content}>
        <div className={styles.search}>
          <Search
            placeholder="Wyszukaj nieruchomość"
            enterButton={<Button type="primary">Wyszukaj</Button>}
            size="large"
            name="searchPhrase"
            onSearch={onSearch}
          />
        </div>
        <div className={styles.flatsWrapper}>flats list</div>
      </div>
    </article>
  )
}

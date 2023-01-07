import { Alert, Button, List } from 'antd';
import Search from 'antd/es/input/Search';
import Title from 'antd/es/typography/Title';
import React from 'react';
import { FlatListItem } from '../../components/flatListItem/FlatListItem';
import { useGetFlatsQuery } from '../../store/api/flat/flatApi';

import styles from "./FlatsPage.module.scss"

export const FlatsPage: React.FC = () => {
  const [searchValue, setSearchValue] = React.useState("");
  const { data, isLoading, isError } = useGetFlatsQuery(searchValue);

  return (
    <article>
      <Title className={styles.title}>Nieruchomości</Title>
      <div className={styles.content}>
        <div className={styles.search}>
          <Search
            placeholder="Wyszukaj nieruchomość"
            enterButton={<Button type="primary">Wyszukaj</Button>}
            size="large"
            name="searchValue"
            onSearch={(e) => setSearchValue(e)}
          />
        </div>
        <div className={styles.flatsWrapper}>
          {isError ? (
            <Alert
              message="Wczytanie mieszkań nie powiodło się"
              description="Spróbuj później"
              type="error"
              showIcon
            />
          ) : (
            <List
              dataSource={data?.flatDtoList}
              loading={isLoading}
              renderItem={flat => <FlatListItem flat={flat} />}
            />
          )}
        </div>
      </div>
    </article>
  )
}

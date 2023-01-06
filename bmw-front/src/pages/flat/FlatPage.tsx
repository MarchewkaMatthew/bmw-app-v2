import Title from 'antd/es/typography/Title';
import React from 'react';
import { useParams } from 'react-router-dom';

export const FlatPage: React.FC = () => {
  const { id } = useParams<{id: string}>();

  return (
    <article>
      <Title>Flat {id}</Title>
      flat info
    </article>
  )
}

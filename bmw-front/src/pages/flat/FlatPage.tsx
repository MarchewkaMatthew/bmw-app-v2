import Title from 'antd/es/typography/Title';
import React from 'react';

interface FlatPageProps {
  id: string;
}

export const FlatPage: React.FC<FlatPageProps> = (props) => {
  const { id } = props
  return (
    <article>
      <Title>Flat {id}</Title>
      flat info
    </article>
  )
}

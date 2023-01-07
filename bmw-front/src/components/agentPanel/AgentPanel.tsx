import { PlusOutlined } from "@ant-design/icons";
import { Alert, Button, List, message, Typography } from "antd";
import Title from "antd/es/typography/Title";
import React, { useState } from "react";
import { useAppUser } from "../../hooks/useAppUser";
import { useModal } from "../../hooks/useModal";
import {
  useAddFlatMutation,
  useGetFlatsQuery,
} from "../../store/api/flat/flatApi";
import { FlatAddRequest } from "../../store/api/flat/types";
import { FlatCardItem } from "../flatCardItem/FlatCardItem";
import { FlatModal, ModalFlat } from "../flatModal/FlatModal";

import styles from "./AgentPanel.module.scss";
const { Text } = Typography;

// // AGENT PANEL

// CZEŚĆ AGENT!
//   LISTA WIADOMOŚCI (TODO: DODAĆ MOŻLIWOŚĆ EDYCJI STATUSU WIADOMOŚCI NA ODPOWIEDZIANA)
//   LISTA SPOTKAŃ + MOLIWOŚĆ ZMIANY SPOTKANIA (TODO: DODAĆ FLAGĘ isCanceled), USUWANIE SPOTKANIA

export const AgentPanel: React.FC = () => {
  const appUser = useAppUser();
  const { data, isLoading, isError, refetch } = useGetFlatsQuery("");
  const [open, showModal, hideModal] = useModal();
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [addFlat] = useAddFlatMutation();
  const [messageApi, contextHolder] = message.useMessage();

  const onFlatModalOk = async (flat: ModalFlat) => {
    setConfirmLoading(true);

    if (appUser._type !== "AUTHENTICATED") {
      throw new Error("User should be authenticated");
    }

    const request: FlatAddRequest = { flatDto: flat };

    addFlat({ body: request, token: appUser.token })
      .then(() => {
        messageApi.loading("Przetwarzanie dodania nieruchomości ...");
      })
      .catch(() => {
        console.log("catch");
        messageApi.error("Dodanie nie powiodło się :(");
      })
      .finally(() => {
        console.log("finally");
        messageApi.destroy();
        refetch();
        hideModal();
        setConfirmLoading(false);
        messageApi.success("Nieruchomość dodana");
      });
  };

  if (appUser._type !== "AUTHENTICATED") {
    return null;
  }

  const { userName } = appUser;

  return (
    <div>
      {contextHolder}
      <Title>{`Cześć ${userName}!`}</Title>
      <Text>Twoja rola to "AGENT"</Text>
      <div className={styles.flatsSection}>
        <div className={styles.sectionTitleContainer}>
          <Title level={2} className={styles.sectionTitle}>
            Nieruchomości
          </Title>
          <Button onClick={showModal} icon={<PlusOutlined />}>
            Dodaj nieruchomość
          </Button>
        </div>
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
            grid={{
              gutter: 16,
              xs: 1,
              sm: 1,
              md: 1,
              lg: 2,
              xl: 3,
              xxl: 4,
            }}
            loading={isLoading}
            renderItem={(flat) => (
              <FlatCardItem flat={flat} onFlatChange={refetch} />
            )}
          />
        )}
        <FlatModal
          mode="ADD"
          open={open}
          onOk={onFlatModalOk}
          onCancel={hideModal}
          confirmLoading={confirmLoading}
        />
      </div>
    </div>
  );
};

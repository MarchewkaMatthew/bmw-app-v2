import {
  EditOutlined,
  PlusCircleOutlined,
  PoweroffOutlined,
} from "@ant-design/icons";
import {
  Badge,
  Button,
  Card,
  List,
  message,
  Tag,
  Tooltip,
  Typography,
} from "antd";
import Meta from "antd/es/card/Meta";
import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useAppUser } from "../../hooks/useAppUser";
import { useModal } from "../../hooks/useModal";
import { useUpdateFlatMutation } from "../../store/api/flat/flatApi";
import { FlatDto, FlatUpdateRequest } from "../../store/api/flat/types";
import { FlatModal, ModalFlat } from "../flatModal/FlatModal";
import styles from "./FlatCardItem.module.scss";

const { Text } = Typography;

interface FlatCardItemProps {
  flat: FlatDto;
  onFlatChange?: () => void;
}

export const FlatCardItem: React.FC<FlatCardItemProps> = (props) => {
  const { flat, onFlatChange } = props;
  const {
    id,
    flatName,
    address,
    priceOfFlat,
    pricePerSquareMeter,
    numberOfRooms,
    floor,
    area,
    constructionYear,
    isActive,
  } = flat;
  const { country, city, district, street } = address;
  const appUser = useAppUser();
  const [updateFlat] = useUpdateFlatMutation();
  const [messageApi, contextHolder] = message.useMessage();
  // MODAL
  const [open, showModal, hideModal] = useModal();
  const [confirmLoading, setConfirmLoading] = useState(false);

  const updateFlatLogic = async (
    newFlatDto: Pick<FlatDto, "id"> & Partial<Omit<FlatDto, "id">>
  ) => {
    if (appUser._type !== "AUTHENTICATED") {
      throw new Error("User should be authenticated");
    }

    const request: FlatUpdateRequest = { flatDto: newFlatDto };

    updateFlat({ body: request, token: appUser.token })
      .then(() => {
        messageApi.loading("Przetwarzanie aktualizacji nieruchomości ...");
      })
      .catch(() => {
        console.log("catch");
        messageApi.error("Zmiana nie powiodła się :(");
      })
      .finally(() => {
        console.log("finally");
        messageApi.destroy();
        messageApi.success("Nieruchomość zaktualizowana");
        if (onFlatChange) {
          onFlatChange();
        }
      });
  };

  const changeFlatStatus = async () => {
    await updateFlatLogic({ id, isActive: !isActive });
  };

  const handleUpdateFlat = async (modalFlat: ModalFlat) => {
    setConfirmLoading(true);
    const formattedFlat = {
      ...modalFlat,
      id,
      address: { ...modalFlat.address, id: flat.address.id },
    };
    await updateFlatLogic(formattedFlat).finally(() => {
      hideModal();
      setConfirmLoading(false);
    });
  };

  const actions = [
    <Tooltip title="Edytuj">
      <Button type="text" onClick={showModal}>
        <EditOutlined key="edit" />
      </Button>
    </Tooltip>,
    <Tooltip title={isActive ? "Dezaktywuj" : "Aktywuj"}>
      <Button type="text" onClick={changeFlatStatus}>
        {isActive ? (
          <PoweroffOutlined key="deactivate" />
        ) : (
          <PlusCircleOutlined key="activate" />
        )}
      </Button>
    </Tooltip>,
  ];

  return (
    <List.Item key={id} className={styles.container}>
      {contextHolder}
      <Badge.Ribbon
        text={isActive ? "Dostępne" : "Niedostępne"}
        color={isActive ? "green" : "red"}
      >
        <Card actions={actions}>
          <Meta
            title={
              <Link to={`/flats/${id}`} target="_blank">
                {flatName}
              </Link>
            }
            description={
              <>
                <Text>
                  ulica: {street}, miasto: {city}, województwo: {district},
                  kraj: {country}
                </Text>
                <ul>
                  <li>Powierzchnia: {area}m2</li>
                  <li>Cena za m2: {pricePerSquareMeter}zł</li>
                  <li>Cena: {priceOfFlat}zł</li>
                </ul>
                <div className={styles.infoRow}>
                  <Tag>Ilość pokoi: {numberOfRooms}</Tag>
                  <Tag>Piętro: {floor}</Tag>
                  <Tag>Rok wybudowania: {constructionYear}</Tag>
                </div>
              </>
            }
          />
        </Card>
      </Badge.Ribbon>
      <FlatModal
        mode="EDIT"
        open={open}
        onOk={handleUpdateFlat}
        onCancel={hideModal}
        confirmLoading={confirmLoading}
        flat={flat}
      />
    </List.Item>
  );
};

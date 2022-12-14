export interface AddressDto {
  id: number;
  street: string;
  district: string;
  city: string;
  country: string;
  postal_code: string;
}

export interface FlatDto {
  id: number;
  flatName: string;
  pricePerSquareMeter: number;
  area: number;
  numberOfRooms: number;
  constructionYear: string;
  floor: number;
  isActive: boolean;
  priceOfFlat: number;
  address: AddressDto;
}

export interface GetFlatsResponse {
  flatDtoList: FlatDto[];
}

export interface GetFlatResponse {
  flatDto: FlatDto;
}

export interface FlatUpdateRequest {
  flatDto: Pick<FlatDto, "id"> & Partial<Omit<FlatDto, "id">>;
}

export interface FlatAddRequest {
  flatDto: Omit<FlatDto, "id" | "address"> & { address: Omit<AddressDto, "id"> };
}
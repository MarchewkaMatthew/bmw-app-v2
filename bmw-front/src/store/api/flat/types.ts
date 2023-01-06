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
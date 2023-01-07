import { useKeycloak } from "@react-keycloak/web";

export type AppUser =
  | { _type: "UNINITIALIZED" }
  | { _type: "NOT_AUTHENTICATED"; login: () => void; register: () => void }
  | { _type: "AUTHENTICATED"; token: string; userName: string; logout: () => void; isAgent: boolean; isCustomer: boolean };

export const useAppUser: () => AppUser = () => {
  const { keycloak, initialized } = useKeycloak();

  if (!initialized) {
    return { _type: "UNINITIALIZED" };
  }

  const { authenticated, login, register, token, tokenParsed, logout } = keycloak;

  if (!authenticated) {
    return { _type: "NOT_AUTHENTICATED", login, register };
  }
  const isAgent = keycloak.tokenParsed?.roles.includes("AGENT");
  const isCustomer = keycloak.tokenParsed?.roles.includes("CUSTOMER");

  return { _type: "AUTHENTICATED", token: token || "ERROR", userName: tokenParsed?.preferred_username || "ERROR", logout, isAgent, isCustomer };
};

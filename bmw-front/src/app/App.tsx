import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { FlatsPage } from '../pages/flat/FlatsPage';
import { ContactPage } from '../pages/contact/ContactPage';
import { FlatPage } from '../pages/flat/FlatPage';
import { WelcomePage } from '../pages/welcome/WelcomePage';
import { Root } from '../pages/Root';
import { ReactKeycloakProvider } from '@react-keycloak/web'
import { Provider } from 'react-redux';
import { store } from '../store/store';

import keycloak from '../Keycloak'

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        path: "/",
        element: <WelcomePage />,
      },
      {
        path: "/contact",
        element: <ContactPage />,
      },
      {
        path: "/flats",
        element: <FlatsPage />,
      },
      {
        path: "/flats/:id",
        element: <FlatPage />,
      },
    ]
  }
]);

export const App: React.FC = () => {

  return (
    <ReactKeycloakProvider authClient={keycloak} initOptions={{
      onLoad: "check-sso",
      checkLoginIframe: false
    }}>
      <React.StrictMode>
        <Provider store={store}>
          <RouterProvider router={router} />
        </Provider>
      </React.StrictMode>
    </ReactKeycloakProvider>
  )
}

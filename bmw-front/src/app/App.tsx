import React from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { FlatsPage } from '../pages/flat/FlatsPage';
import { ContactPage } from '../pages/contact/ContactPage';
import { FlatPage } from '../pages/flat/FlatPage';
import { WelcomePage } from '../pages/welcome/WelcomePage';
import { Root } from '../pages/Root';

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
    <RouterProvider router={router} />
  )
}

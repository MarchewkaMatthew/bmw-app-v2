/* eslint-disable testing-library/prefer-screen-queries */
import { test, expect } from '@playwright/test';

test('agent login flow', async ({ page }) => {
  await page.goto('http://localhost:3000/');

  // redirects to keycloak
  await page.getByTestId("login").click();

  // fill in the form
  await page.getByLabel("Username or email").fill("agent");
  await page.getByLabel("Password").fill("agent");

  // submit
  const submitButton = page.getByRole('button', { name: 'Sign In' });
  await submitButton.focus();
  await expect(page).toHaveScreenshot('keycloak.png');
  await submitButton.click();

  // expect
  await expect(page.getByText('Cześć agent!')).toBeVisible();
});

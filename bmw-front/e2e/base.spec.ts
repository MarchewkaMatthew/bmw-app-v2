/* eslint-disable testing-library/prefer-screen-queries */
import { test, expect } from '@playwright/test';

test('has title', async ({ page }) => {
  await page.goto('http://localhost:3000/');

  await expect(page).toHaveTitle(/BMW client/);
});

test('has working contact link', async ({ page }) => {
  await page.goto('http://localhost:3000/');

  // Click the get started link.
  await page.getByRole('link', { name: 'Kontakt' }).click();

  // Expects the URL to contain intro.
  await expect(page).toHaveURL(/.*contact/);
});

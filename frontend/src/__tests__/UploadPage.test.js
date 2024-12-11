import { render, screen } from '@testing-library/react';
import UploadPage from '../pages/UploadPage';

test('renders upload page heading', () => {
  render(<UploadPage />);
  const heading = screen.getByText(/Upload CSV/i);
  expect(heading).toBeInTheDocument();
});

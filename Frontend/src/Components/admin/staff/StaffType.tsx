// Define a TypeScript interface for Staff
interface StaffType {
    id: number;
    firstName: string;
    middleName: string | null; // Middle name could be null if not provided
    lastName: string;
    fullName: string; // Calculated in the application, not stored in the database
    username: string;
    password: string;
    email: string;
    phoneNo: string;
    gender: string;
    birthdate: string; // LocalDate mapped to a string format (e.g., 'YYYY-MM-DD')
    role: string;
  }

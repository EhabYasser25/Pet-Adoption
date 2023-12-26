import { useNavigate } from 'react-router-dom';
import './navbar-admin.css'; // Make sure you have the CSS file for styling

const NavBar = () => {
  const navigate = useNavigate();
  const adminName = "Admin Name"; // Replace with dynamic admin name if needed

  const handleSignOut = () => {
    // Perform sign out logic here
    console.log('Signing out');

    // Redirect to sign-in page or root after sign out
    navigate('/signin');
  };

  return (
    <nav className="navbar">
      <h1>Admin Dashboard</h1>
      <div className="navbar-right">
        <span className="signed-in-as">Signed in as {adminName}</span>
        {/* Sign out button */}
        <button onClick={handleSignOut} className="sign-out-button">
          Sign Out
        </button>
      </div>
    </nav>
  );
};

export default NavBar;
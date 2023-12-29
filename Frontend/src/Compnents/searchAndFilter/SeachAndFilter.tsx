import React from 'react'

const SeachAndFilter = () => {
  return (
    <aside className="">
    <ul>
      <li onClick={() => setActiveSection('shelters')}>
        <FaHome className="admin-icon" /> Shelters
      </li>
      <li onClick={() => setActiveSection('addShelter')}>
        <FaPaw className="admin-icon" /> Add Shelter
      </li>
    </ul>
  </aside>
  )
}

export default SeachAndFilter
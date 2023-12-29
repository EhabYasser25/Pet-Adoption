export const getId = () => {
  return parseInt(window.sessionStorage.getItem("id"))
}

export const getName = () => {
  return window.sessionStorage.getItem("name")
}

export const getEmail = () => {
  return window.sessionStorage.getItem("email")
}

export const getPhone = () => {
  return window.sessionStorage.getItem("phone")
}

export const getAddress = () => {
  return window.sessionStorage.getItem("address")
}

export const getBalance = () => {
  return parseFloat(window.sessionStorage.getItem("balance"))
}

export const getNationalID = () => {
  return window.sessionStorage.getItem("nationalID")

}

export const getDateJoined = () => {
  return window.sessionStorage.getItem("dateJoined")
}

export const getSellerDescription = () => {
  return window.sessionStorage.getItem("sellerDescription")
}

export const getVatRegistrationNumber = () => {
  return window.sessionStorage.getItem("vatRegistrationNumber")
}

export const getJwtToken = () => {
  return window.sessionStorage.getItem("jwtToken")
}

export const setJwtToken = (jwtToken: string) => {
  window.sessionStorage.setItem("jwtToken", jwtToken)
}

export const clearCurrentSession = () => {
  window.sessionStorage.clear();
};

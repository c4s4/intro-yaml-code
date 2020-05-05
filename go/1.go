var user User
source, err := ioutil.ReadFile("user.yml")
if err != nil {
  panic(err)
}
err = yaml.Unmarshal(source, &user)
if err != nil {
  panic(err)
}
fmt.Printf("user: %v\n", user)

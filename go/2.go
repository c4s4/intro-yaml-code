var thing interface{}
source, err := ioutil.ReadFile("user.yml")
if err != nil {
  panic(err)
}
err = yaml.Unmarshal(source, &thing)
if err != nil {
  panic(err)
}
fmt.Printf("Thing: %#v\n", thing)

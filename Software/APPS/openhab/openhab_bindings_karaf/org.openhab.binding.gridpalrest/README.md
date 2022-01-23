# Gridpal REST Binding

The openHAB REST Binding allows REST calls at `http://localhost:8080/rest/gridpalrest/{get,post}`.

## GET

```js
curl http://localhost:8080/rest/gridpalapi/get/Minhaz?q=Foo
{
    "method": "GET",
    "name": "Minhaz",
    "query": "Foo"
}
```

## POST

```js
curl -s -H "Content-Type: application/json" http://localhost:8080/rest/gridpalapi/post --data '{"name": "Kanok"}'
{
    "method": "POST",
    "name": "Kanok"
}
```

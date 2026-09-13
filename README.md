## Springboot Http QUERY method (RFC 10008)
This is a demonstration of the newly released <a href="https://www.rfc-editor.org/rfc/rfc10008.html">HTTP QUERY method</a> applied to Java Springboot.

The demo simulates an in memory order collection.  
`GET /api/v1/orders` returns the full collection.  
`QUERY /api/v1/orders` allows the client to use a request body to filter for specific orders using the optional `OrderQuery`.  

A client can now filter for orders using the `OrderQuery` as the request body instead of the default URL search params.  

| Field | Type | Constraint |
|---|---|---|
| `customer` | string | case-insensitive substring of the customer name |
| `statuses` | string[] | order status is one of these (`OPEN`, `PAID`, `CANCELLED`) |
| `minTotal` | number | order total ≥ this amount |
| `maxTotal` | number | order total ≤ this amount |
| `from` | date (`YYYY-MM-DD`) | created on or after |
| `to` | date (`YYYY-MM-DD`) | created on or before |
| `tags` | string[] | order has **all** of these tags |
| `shipping` | object | nested address filter |
| `shipping.city` | string | exact city, case-insensitive |
| `shipping.country` | string | exact country, case-insensitive |
### Example Query

`QUERY /api/v1/orders`  
`Content-Type: application/json`
```json
{
  "customer": "John Smith",
  "statuses": ["OPEN", "PAID"],
  "minTotal": 40,
  "maxTotal": 200,
  "from": "2026-08-01",
  "to": "2026-09-30",
  "tags": ["vip"],
  "shipping": {
    "city": "Stockholm",
    "country": "SE"
  }
}
```

```shell
curl -X QUERY http://localhost:8080/api/v1/orders \
  --json '{"customer":"John Smith","statuses":["OPEN","PAID"],"minTotal":40,"shipping":{"country":"SE"}}'
```
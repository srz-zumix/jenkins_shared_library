# [szCreateQRCode](https://github.com/srz-zumix/jenkins_shared_library/blob/main/vars/szCreateQRCode)

Create a QR code and writefile to workspace

## Reference

### Definition

```groovy
szCreateQRCode( Map params = [:], contents, path)
```

### Parameters

| parameter     | requirement | description                | note |
|:--------------|:-----------:|:---------------------------|:-----|
| contents      | o           | qr code contents           |      |
| path          | o           | output file path           |      |
| width         |             | image width (default: 300) |      |
| height        |             | image height (default: 300)|      |
| imageForamt   |             | image format (default: png)|      |

### Usage

```groovy
steps {
  szCreateQRCode("https://github.com/srz-zumix/jenkins_shared_library", 'qrcode.png', widht: 200, height: 200)
}
```

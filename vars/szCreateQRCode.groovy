#!/usr/bin/groovy
@Grapes([
@Grab(group='com.google.zxing', module='core', version='3.4.1'),
@Grab(group='com.google.zxing', module='javase', version='3.4.1')
])

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.nio.file.Paths
import javax.imageio.ImageIO

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

@NonCPS
def create_base64_data(contents, imageForamt, width, height) {
    Hashtable hints = new Hashtable()
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M)

    QRCodeWriter qrWriter = new  QRCodeWriter()
    BitMatrix bitMatrix = qrWriter.encode(contents, BarcodeFormat.QR_CODE, width, height, hints)
    BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix)
    ByteArrayOutputStream baos = new ByteArrayOutputStream()
    ImageIO.write(image, imageForamt, baos)

    return baos.toByteArray().encodeBase64().toString()
}

@NonCPS
def getPath(path) {
    return Paths.get(pwd(), path).toAbsolutePath().toString()
}

def write(contents, path, imageForamt, width, height) {
    def dataText = create_base64_data(contents, imageForamt, width, height)

    def imageFile = getPath(path)
    writeFile encoding: 'Base64', file: "${imageFile}", text: "${dataText}"
}

def call(Map params = [:], contents, path) {
    imageForamt = params.imageForamt ?: 'png'
    width = params.width ?: 300
    height = params.height ?: 300
    write(contents, path, imageForamt, width, height)
}

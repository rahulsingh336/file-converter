@ECHO OFF
java -DinputFilePath=E:\wks\file-converter\files -DinputFileName="DSV input 1.txt" -DoutputFilePath=E:\wks\file-converter\files -DoutputFileName="DSV outPut.jsonl" -jar target/file-converter-1.0-SNAPSHOT-spring-boot.jar
pause


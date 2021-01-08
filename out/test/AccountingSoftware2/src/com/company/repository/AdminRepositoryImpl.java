package com.company.repository;

import java.io.*;

import static com.company.util.Constants.*;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public void insetLine(String csvLine) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(CSV_ADMIN_USERS, true);
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(csvLine);

            System.out.println("Admin user was added successfully!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter!!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ex) {
                System.out.println("Error while closing or flushing fileWriter!");
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void deleteLine(String username) {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            br = new BufferedReader(new FileReader(CSV_ADMIN_USERS));
            sb = new StringBuilder();
            String line;
            boolean firstLineFlag = true;
            while ((line = br.readLine()) != null) {
                if (firstLineFlag) {
                    firstLineFlag = false;
                    sb.append(line);
                    sb.append(NEW_LINE_SEPARATOR);
                } else {
                    String[] lineValues = line.split(COMMA_DELIMITER);
                    if (lineValues[0] != username) {
                        sb.append(line);
                        sb.append(NEW_LINE_SEPARATOR);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("There was a error while deleting!");
            try {
                br.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        writeAllInformationToDatabase(sb);
    }

    @Override
    public void updateLine(String password) {
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            br = new BufferedReader(new FileReader(CSV_FILE_NAME));
            sb = new StringBuilder();
            String line;
            boolean firtLineFlag = true;
            while ((line = br.readLine()) != null) {
                if (firtLineFlag) {
                    firtLineFlag = false;
                    sb.append(line);
                    sb.append(NEW_LINE_SEPARATOR);
                } else {
                    String[] lineValues = line.split(COMMA_DELIMITER);
                    if (lineValues[0] == password) {
                        sb.append(password);
                        sb.append(NEW_LINE_SEPARATOR);
                    } else {
                        sb.append(line);
                        sb.append(NEW_LINE_SEPARATOR);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("There was an error while updating!");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeAllInformationToDatabase(sb);
    }
    private void writeAllInformationToDatabase(StringBuilder sb) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(String.valueOf(CSV_ADMIN_USERS)));
            fw.write(sb.toString());
        } catch (Exception e) {
            System.out.println("There was a error while writing information after a delete!");
            e.printStackTrace();
        } finally {
            try {
                if (fw != null){
                    fw.flush();
                    fw.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}

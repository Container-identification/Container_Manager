import logging


def getValidNum(top_10_string):
    dictA = {
        'A': 10,
        'B': 12,
        'C': 13,
        'D': 14, 'E': 15, 'F': 16, 'G': 17, 'H': 18, 'I': 19, 'J': 20,
        'K': 21, 'L': 23, 'M': 24, 'N': 25, 'O': 26, 'P': 27, 'Q': 28,
        'R': 29, 'S': 30,
        'T': 31, 'U': 32, 'V': 34, 'W': 35, 'X': 36, 'Y': 37, 'Z': 38,
        '1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '0': 0,
    }
    sum = 0

    for i, char in enumerate(top_10_string):
        sum += dictA[char] * pow(2, i)

    if sum % 11 == 10:
        sum = 0

    return sum % 11


def formatFilter():
    book = True
    with open("../other/result_nofilter.txt", 'r') as f:
        data = f.readlines()
        out_string = ''
        for line in data:
            line = line.strip('\n')
            print(line)
            if book:
                picture_id = line.lstrip("img_")
                char_4 = ''
                num_6 = ''
                cls_4 = ''
                book = not book
            else:
                book = not book
                result_list = eval(line)
                for each in result_list:
                    if len(each) == (0 or 1):
                        continue
                    if len(each) == 6:
                        num_6 = each
                    if len(each) == 4:
                        if str.isalpha(each):
                            char_4 = each
                        else:
                            cls_4 = each
                    if len(each) == 5 or 2 or 3 or len(each) >= 7:
                        logging.info("{} this picture recognize wrong, plz restart.".format(picture_id))

                # print(picture_id, char_4, num_6, cls_4)
                out_string += picture_id + ' ' + char_4 + num_6 + str(getValidNum(char_4 + num_6)) + ' ' + cls_4 + '\n'
                # print(out_string)

        with open('../new_result.txt', 'w') as f_result:
            f_result.write(out_string)
        f_result.close()
    f.close()

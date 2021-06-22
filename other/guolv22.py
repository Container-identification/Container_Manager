import math
def makesplit(loclist):
    singlelist = []
    make = []
    makestr1 = ''
    makestr3 = ''
    makestr2 = ''
    cerlist = []
    for j in loclist:
        j = j.strip()
        restr = j.split(',')

        if (len(restr[-2].split('\'')[-2]) > 4 and len(restr[-2].split('\'')[-2]) > len(makestr2)):
            makestr2 = restr[-2].split('\'')[-2]

        else:
            cerlist.append(restr[-3])
            if (len(restr[-2].split('\'')) < 2):
                singlelist.append(restr[-2].split('\'')[-1])
            singlelist.append(restr[-2].split('\'')[-2])
    if len(cerlist) == 2:
        if int(cerlist[0][0:3]) > int(cerlist[1][0:3]):
            makestr1 = singlelist[1]
            makestr3 = singlelist[0]

        else:
            makestr1 = singlelist[0]
            makestr3 = singlelist[1]
    make.append(makestr1)
    make.append(makestr2)
    make.append(makestr3)
    #     print (make)
    return make
def makesplit2(loclist):
    singlelist=[]
    make=[]
    makestr1=''
    makestr3=''
    makestr2=''
    for j in loclist:
        #print(j)
        j=j.strip()
        restr=j.split(',')
#         print(restr)
        if len(restr[-2].split('\''))<2:
             singlelist.append(restr[-2].split('\'')[-1])
        singlelist.append(restr[-2].split('\'')[-2])
    for k in singlelist:
        k=k.strip()
        if(len(k)>5):
            str1=k.split(" ")
            p=0
            for i in str1:
                if(len(i)==6):
                    makestr1=i
                    p+=1
                if(len(i)==4):
                    makestr1=i
                    p+=1
            if(p==0 and len(k)<10and len(str1)>2):
                makestr2=str1[1]+str1[2]
                makestr1=str1[0]
            elif(len(str1)==1):
                makestr2=str1[0][4:10]
                makestr1=str1[0][0:4]
        else:
            makestr3=k
    make.append(makestr1)
    make.append(makestr2)
    make.append(makestr3)
    return make


def makespilt3(loclist):
    singlelist = []
    make = []
    makestr1 = ''
    makestr3 = ''
    makestr2 = ''
    cerlist = {}
    loc = 0
    j = 0
    for j in loclist:
        j = j.strip()
        #         print(j)
        restr = j.split(',')
        #         print(restr[-2].split('\'')[-2])
        if (restr[-3][1:4] not in cerlist.keys()):
            k = 1
            if (restr[-3][1].isdigit() and restr[-3][2] != '.'):
                k = 2
            if (restr[-3][2].isdigit() and restr[-3][3] != '.'):
                k = 3
            if (restr[-3][3].isdigit() and restr[-3][4] != '.'):
                k = 4
            #             if restr[-3][k]=='.':
            #                 k-=1
            cerlist[restr[-3][1:k]] = restr[-2].split('\'')[-2]
    cerlist = sorted(cerlist.items(), key=lambda d: d[0])[0:5]
    # print(cerlist)
    k = 0
    list22 = []
    p = ''
    for i in cerlist:
        if i[1].isalpha():
            p = i[1]
        if len(i[1]) == 3:
            k += 1
            # print(i[1])
            list22.append(i[1])
        if len(i[1]) < 4:
            continue
        if len(i[1]) > 4 and i[1].isalpha() == False:
            makestr2 = i[1]
        if len(i[1]) == 4:
            if loc < int(i[0]):
                j = loc
                makestr1 = makestr3
                makestr3 = i[1]

            else:
                makestr1 = i[1]
    if len(makestr2) == 0 and k >= 2:
        makestr2 = list22[0] + list22[1]
    if len(makestr1) == 0:
        makestr1 = p[0:4]
    make.append(makestr1)
    make.append(makestr2)
    make.append(makestr3)
    #     print(makestr1)
    return make

def two(x):
    return math.pow(2,x-1)
def judge(str1):
    codeList = ['22G1', '25G1', '22V1', '22U1', '22R1', '25R1', '22p1','22T1',  '42G1', '45G1', '42V1', '42U1', '42R1',
                '45R1', '42T1', '42P1', 'L2G1', 'L5G1', 'L2V1',
                'L2U1', 'L2R1', 'L5R1', 'L2T1', 'L2P1']
    if(len(str1)==4):
        if str1 in codeList:
            return str1
        else:
            max=0
            maxstr=''
            k=0
            for i in codeList:
                p=0
                k=0
                for j in i:
                    if j in str1:
                        p+=1
                if p>max:
                    max=p
                    maxstr=i
            if(max>0):
                return maxstr
    if(len(str1)==3):
        for i in codeList:
            if i[0:3]==str1 or i[1:4]==str1:
                return i
    if(len(str1)>4):
        for i in range(len(str1)-3):
            for j in codeList:
                if str1[i:i+4]==j:
                    return j
    return str1
def deal(resultspilt):
    k = ''
    for i in resultspilt.keys():
        j = resultspilt[i]
        # print(j)
        if (len(j) < 3):
            continue
        if j[2].isalpha():
            k = resultspilt[i][2]
            resultspilt[i][2] = resultspilt[i][0]
            resultspilt[i][0] = k
        if 'I' in resultspilt[i][2]:
            resultspilt[i][2] = resultspilt[i][2].replace('I', '1')
        if 'A' in resultspilt[i][2]:
            resultspilt[i][2] = resultspilt[i][2].replace('A', '4')
        if 'J' in resultspilt[i][2]:
            resultspilt[i][2] = resultspilt[i][2].replace('J', '5')
        resultspilt[i][2]=judge(resultspilt[i][2])
        # if resultspilt[i][2] == '5R1':
        #     resultspilt[i][2] = "45R1"
        # if resultspilt[i][2] == '2R1':
        #     resultspilt[i][2] = "22R1"
        # if resultspilt[i][2] == '22R':
        #     resultspilt[i][2] = "22R1"
        # if resultspilt[i][2] == '5R1':
        #     resultspilt[i][2] = "45R1"
        # if resultspilt[i][2] == '2G1':
        #     resultspilt[i][2] = "22G1"
        # if resultspilt[i][2] == '22G':
        #     resultspilt[i][2] = "22G1"
        #         if 'N' in resultspilt[i][2]:
        #              resultspilt[i][2] = resultspilt[i][2].replace('N', '2')
        #         if resultspilt[i][2][-1]!='1':
        #              resultspilt[i][2] = resultspilt[i][2].replace(resultspilt[i][2][-1], '2')
        if len(resultspilt[i][1]) == 7:
            resultspilt[i][1] = resultspilt[i][1][0:6]
        if len(resultspilt[i][1]) >= 10:
            resultspilt[i][0] = resultspilt[i][1][0:4]
            resultspilt[i][1] = resultspilt[i][1][4:10]

def answer():
    with open("annotation.txt", "r") as f:
        i = 0
        j = 0
        numberlist = {}
        for line in f.readlines():
            line = line.strip('\n')
            list1 = line.split(' ')
            list2 = line.split('.')
            j = list2[0]
            numberlist[j] = [str(list1[1][0:4]), str(list1[1][4:10]), list1[2]]
    return numberlist
def cal(s):
    dictA={
        'A':10,
        'B':12,
        'C':13,
        'D':14,'E':15,'F':16,'G':17,'H':18,'I':19,'J':20,
        'K':21,'L':23,'M':24,'N':25,'O':26,'P':27,'Q':28,
        'R':29,'S':30,
        'T':31,'U':32,'V':34,'W':35,'X':36,'Y':37,'Z':38
    }

    sum=0
    for i in range(len(s)+1):
        if i==0:
            continue
        if ord(s[i-1])<64:
            sum+=(ord(s[i-1])-48)*two(i)
        else:
            sum += dictA[s[i-1]] * two(i)
    if sum%11==10:
        sum=0
    return sum%11

def chuMain():
    resultlist = []
    with open('other//result_nofilter.txt') as f:
        for line in f.readlines():
            line = line.split('),')
            resultlist.append(line)
    resultspilt = {}
    for loclist in resultlist:
        if len(loclist) == 1 and '.jpg' in loclist[0]:  # 列表元素只有一个 首位元素含JPG
            i = loclist[0].strip()
            i = i.split('.jpg')
            i = i[0]
            j = i.split('\'')
            j = j[-1]
            #print(j)
            continue
        elif (len(loclist) == 1):
            resultspilt[j] = []
        elif (len(loclist) == 3):
            make = makesplit(loclist)
            resultspilt[j] = make
        elif (len(loclist) == 2):
            make = makesplit2(loclist)
            resultspilt[j] = make
        else:
            make = makespilt3(loclist)
            resultspilt[j] = make
    deal(resultspilt)
    f = open("result.txt", "w+")
    resultspilt=sorted(resultspilt.items(), key=lambda d: int(d[0]))
    # 图片名必须为数字
    print(resultspilt)
    for i in resultspilt:

        p = i[0]
        j=i[1]
        # print(len(j))

        if(len(j)<3):
            f.write(p + '.jpg '+"\n")
            continue
        else:
            k=cal(j[0]+j[1])


            # print(i+" "+j[1])
            #print(j)
            f.write(str(int(p)) + '.jpg ' + j[0]+j[1]+str(int(k))+' '+j[2] )
            f.write("\n")

if __name__ == '__main__':
    chuMain()

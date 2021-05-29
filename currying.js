let generateFullName = x => y => x + y;
let firstName = '田中';
let lastNameToFullName = generateFullName(firstName);
lastNameToFullName('太郎');
lastNameToFullName('花子');

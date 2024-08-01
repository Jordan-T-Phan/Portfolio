$(document).ready(function () {
    const numberOfButtonsEasy = 5;
    const numberOfButtonsMed = 10;
    const numberOfButtonsIns = 50;
    let timeCount;
    let timer;
    let difficultyLevel = 0;
    let scoreGuessed;
    let scoreCorrect;
    let firstNumber;
    let secondNumber;
    let answer;
    let randomNumbers;
    let randomNumber;
    let randomPosistion;
    let easyMin;
    let easyMax;
    let medMin;
    let medMax;
    let insMin;
    let insMax;


    reset(0);

    generateQuestion(easyMin, easyMax, numberOfButtonsEasy);

    $('#radio input').click(function () {
        if ($('input[name=btnradio]:checked', '#radio').val() === "0") {
            if (difficultyLevel !== 0) {
                radioHelpReset();
                difficultyLevel = 0;
                generateQuestion(easyMin, easyMax, numberOfButtonsEasy);
            }
        }
        if ($('input[name=btnradio]:checked', '#radio').val() === "1") {

            if (difficultyLevel !== 1) {
               radioHelpReset();
                difficultyLevel = 1;
                generateQuestion(medMin, medMax, numberOfButtonsMed);
            }
        }
        if ($('input[name=btnradio]:checked', '#radio').val() === "2") {

            if (difficultyLevel !== 2) {
               radioHelpReset()
                difficultyLevel = 2;
                generateQuestion(insMin, insMax, numberOfButtonsIns);

            }
        }
    });
    $('#reset').click(function () {
            reset(0)
            $('#button-container').empty();
            $('#feedback').text('');
            $('#score').text(`${scoreCorrect}/ ${scoreGuessed}`);
            if (difficultyLevel === 0) {
                generateQuestion(easyMin, easyMax, numberOfButtonsEasy);
            }
            if (difficultyLevel === 1) {
                generateQuestion(medMin, medMax, numberOfButtonsMed);
            }
            if (difficultyLevel === 2) {
                generateQuestion(insMin, insMax, numberOfButtonsIns);
            }
        }
    );


    function generateRandomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + 1);
    }


    function generateRandomNumberPosistion(number) {
        return Math.floor(Math.random() * number);
    }

    function handleButtonClick(selectedNumber) {
    clearInterval(timer);
        if (selectedNumber === answer) {
            scoreGuessed++;
            scoreCorrect++;
            $('#feedback').text(`Correct.`).css('color', 'green');

        } else {
            scoreGuessed++;
            $('#feedback').text(`Wrong. ${firstNumber}*${secondNumber}=${answer}`).css('color', 'red');

        }
        $('#score').text(`${scoreCorrect}/ ${scoreGuessed}`);
        reset(1);

        if (difficultyLevel === 0) {
            generateQuestion(easyMin, easyMax, numberOfButtonsEasy);
        }
        if (difficultyLevel === 1) {
            generateQuestion(medMin, medMax, numberOfButtonsMed);
        }
        if (difficultyLevel === 2) {
            generateQuestion(insMin, insMax, numberOfButtonsIns);
        }

    }


    function generateQuestion(min, max, buttons) {
        clearInterval(timer);
        randomPosistion = generateRandomNumberPosistion(buttons);
        firstNumber = generateRandomNumber(min, max);
        secondNumber = generateRandomNumber(min, max);
        answer = firstNumber * secondNumber;
        randomNumbers = [];
        randomNumbers.push(answer);
        while (randomNumbers.length < numberOfButtonsEasy) {
            let count = 0;
            while (count < (randomPosistion)) {
                randomNumber = generateRandomNumber(min * min, max * max);
                if (!randomNumbers.includes(randomNumber) && randomNumber < answer) {
                    randomNumbers.push(randomNumber);
                    count++;
                }
            }
            count = 0;
            while (count < (buttons - randomPosistion - 1)) {
                randomNumber = generateRandomNumber(min * min, max * max);
                if (!randomNumbers.includes(randomNumber) && randomNumber > answer) {
                    randomNumbers.push(randomNumber);
                    count++;
                }
            }
        }
        randomNumbers.sort((a, b) => a - b);

        if (difficultyLevel === 2) {
                $('#time').attr({
                class: "text-center alert alert-danger fs-5 fw-bold",
                role: "alert"
            }).text('Time: ' + timeCount--);
            timer = setInterval(countingTime, 1000);
        } else {
            clearInterval(timer);
            $('#time').attr({class: "", role: ""}).text('');
        }
        $('#button-container').empty();
        for (let i = 0; i < randomNumbers.length; i++) {
            const button = $('<button>').attr({class: 'btn btn-lg btn-primary m-1'}).text(randomNumbers[i]);
            // set an event listener for click event, when clicked it calls our handleButtonClick function and pass the randomNumber
            button.click(function () {
                handleButtonClick(randomNumbers[i]);
            });
            // Append the button the container
            $('#button-container').append(button)
        }

        $('#firstNumber').text(firstNumber);
        $('#secondNumber').text(secondNumber);
        console.log("ready!");

    }

    function reset(value) {
        if (value === 0) {
            scoreGuessed = 0;
            scoreCorrect = 0;
        }
        timeCount = 10;
        firstNumber = 0;
        secondNumber = 0;
        answer = 0;
        randomNumbers = [];
        randomNumber = 0;
        randomPosistion = 0;
        easyMin = 1;
        easyMax = 10;
        medMin = 10;
        medMax = 100;
        insMin = 10;
        insMax = 1000;
    }

    function countingTime() {
        if (timeCount === 0) {
            clearInterval(timer);
            scoreGuessed++;
            reset(1);
            $('#feedback').text('No Answer').css('color','grey');
            $('#score').text(`${scoreCorrect}/ ${scoreGuessed}`);
            generateQuestion(insMin, insMax, numberOfButtonsIns);
        } else {
            $('#time').attr({
                class: "text-center alert alert-danger fs-5 fw-bold",
                role: "alert"
            }).text('Time: ' + timeCount--);
        }
    }
    function radioHelpReset(){
         reset(1)
                $('#feedback').text('');
                $('#score').text(`${scoreCorrect}/ ${scoreGuessed}`);
    }
})
;



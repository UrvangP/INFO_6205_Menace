// JS Prototype

// PSA Project


// Total combinations: 19,683

// Optimize it!!

const allowedLetters = [1, 2, 0];
const ofLetter = 9;

let obj = {}

var verify = (state) => {
  let xs = 0;
  let os = 0;

  let i = 0;
  let temp = []
  while (i < state.length) {
    if (state[i] == 1) xs++;
    if (state[i] == 2) os++;
    if (!state[i]) temp.push(i);
    i++;
  }


  if (xs == 0 && os == 0) return {
    val: false,
    data: temp
  };


  if (xs == os || xs == (os - 1) || os == (xs - 1)) return {
    val: true,
    data: temp
  };

  return {
    val: false,
    data: temp
  };
}


var recur = function(track) {
  if (track.length == ofLetter) {
    const data = verify(track);
    if (data.val) obj[track.join("")] = data.data;
    return
  }
  for (let i = 0; i < allowedLetters.length; i++) {
    track.push(allowedLetters[i]);
    recur(track);
    track.pop();
  }
}

recur([]);

console.log(obj);
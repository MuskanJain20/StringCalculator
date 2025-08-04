import React, { useState } from 'react';

function StringCalculator() {
  const [input, setInput] = useState('');
  const [sum, setSum] = useState(null);
  const [error, setError] = useState('');

  const calculateSum = () => {
    setError('');
    setSum(null);
    let numbers = input;

    if (numbers.trim() === '') {
      setSum(0);
      return;
    }

    let delimiterRegex = /,|\n/; // default delimiters

    if (numbers.startsWith('//')) {
      const newlineIndex = numbers.indexOf('\n');
      const delimiterSection = numbers.substring(2, newlineIndex);
      numbers = numbers.substring(newlineIndex + 1);

      const delimiters = [];
      const bracketPattern = /\[(.*?)]/g;
      let match;
      while ((match = bracketPattern.exec(delimiterSection)) !== null) {
        delimiters.push(escapeRegExp(match[1]));
      }

      // If no brackets found, treat as single-char delimiter
      if (delimiters.length === 0) {
        delimiters.push(escapeRegExp(delimiterSection));
      }

      delimiterRegex = new RegExp(delimiters.join('|'));
    }

    const parts = numbers.split(delimiterRegex);
    let total = 0;
    const negatives = [];

    for (let part of parts) {
      if (part.trim() === '') continue;
      const number = parseInt(part.trim());
      if (isNaN(number)) continue;
      if (number < 0) {
        negatives.push(number);
      }
      total += number;
    }

    if (negatives.length > 0) {
      setError(`Negatives not allowed: ${negatives.join(', ')}`);
    } else {
      setSum(total);
    }
  };

  const escapeRegExp = (string) => {
    return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
  };

  return (
    <div>
      <h2 className="text-xl font-bold mb-4">String Calculator</h2>
      <textarea
        rows={4}
        className="w-full p-2 border rounded mb-4"
        placeholder="Enter numbers (e.g. 1,2\n3 or //;\n1;2 or //[***][%]\n1***2%3)"
        value={input}
        onChange={(e) => setInput(e.target.value)}
      />
      <button
        onClick={calculateSum}
        className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
      >
        Calculate
      </button>
      {error && <div className="text-red-500 mt-4">{error}</div>}
      {sum !== null && <div className="text-green-600 mt-4 font-semibold">Sum: {sum}</div>}
    </div>
  );
}

export default StringCalculator;
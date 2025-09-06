#!/usr/bin/python3
import pydoc
import sys
import os

# Caesar encoding, for use with decoding below
ENCODING = {
    'y': 'a',
    'h': 'b',
    'v': 'c',
    'x': 'd',
    'k': 'e',
    'p': 'f',
    'z': 'g',
    's': 'h',
    'a': 'i',
    'b': 'j',
    'e': 'k',
    'w': 'l',
    'u': 'm',
    'q': 'n',
    'n': 'o',
    'l': 'p',
    'm': 'q',
    'f': 'r',
    'o': 's',
    'i': 't',
    'g': 'u',
    'j': 'v',
    't': 'w',
    'd': 'x',
    'r': 'y',
    'c': 'z',
    '3': '0',
    '8': '1',
    '4': '2',
    '0': '3',
    '2': '4',
    '7': '5',
    '5': '6',
    '9': '7',
    '1': '8',
    '6': '9'
 }

"""An ingredient has an amount and a description.
For example: an Ingredient could have "1 cup" as the amount and "butter" as the description."""
class Ingredient():
    def __init__(self, amount, description) -> None:
        self.amount = amount
        self.description = description


def decode_string(str):
    """Given a string named str, use the Caesar encoding above to return the decoded string."""
    # TODO: implement me
    decoded = ""
    for char in str:
        if char in ENCODING:
            decoded += ENCODING[char]
        else:
            decoded+= char
    return decoded




def decode_ingredient(line):
    """Given an ingredient, decode the amount and description, and return a new Ingredient"""
    line = decode_string(line)
    ingredients = line.split("#")
    return Ingredient(ingredients[0], ingredients[1])



    



def main():
    """A program that decodes a secret recipe"""
    # TODO: implement me
    with open('decoded_recipe.txt', 'w') as output:
        with open("secret_recipe.txt", "r") as file:
            for line in file:
                ingredient = decode_ingredient(line)
                print(ingredient.amount + " " + ingredient.description, file=output, end="")

if __name__ == "__main__":
    main()

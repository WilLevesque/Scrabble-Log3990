/* eslint-disable @typescript-eslint/no-magic-numbers */
export const MAX_MESSAGE_LENGTH = 512;
export const NEW_GAME_TIMEOUT = 5000;
const DB_USER = 'server';
const DB_PSW = '3y6w6jmvKgBR0vJG';
const CLUSTER_URL = 'scrabblecluster.mqtnr.mongodb.net';
export const DATABASE_URL = `mongodb+srv://${DB_USER}:${DB_PSW}@${CLUSTER_URL}/<dbname>?retryWrites=true&w=majority`;
export const DATABASE_NAME = 'scrabble';
export const USER_COLLECTION = 'users';
export const USER_CREDS_COLLECTION = 'userCreds';
export const MAX_FILE_LENGTH = 235;
export const PERCENTAGE_OF_POINTS_TO_SPLIT = 0.25;
export const SESSION_MAX_AGE = 24 * 60 * 60 * 1000;
export const EXPRESS_SESSION_SECRET = 'SUPER_SECURE_SCRABBLE_KEY_12345678';
export const REDIS_URL = 'redis://redis-15767.c9.us-east-1-2.ec2.cloud.redislabs.com:15767';
export const REDIS_USER = 'default';
export const REDIS_PWD = '45nWN3tcOm2boSIiI1RWtQmsU3X9rTyu';

// TODO set this to enable api access only for logged in user
export const ENABLE_API_LOGIN = false;
export const ENABLE_SOCKET_LOGIN = false;

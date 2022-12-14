import React, { useState } from 'react';
import { Button, Input } from 'antd';

import { useAppSelector, useAppDispatch } from '../../store/hooks';
import {
  decrement,
  increment,
  incrementByAmount,
  incrementAsync,
  incrementIfOdd,
  selectCount,
} from './counterSlice';
import styles from './Counter.module.css';

export function Counter() {
  const count = useAppSelector(selectCount);
  const dispatch = useAppDispatch();
  const [incrementAmount, setIncrementAmount] = useState('2');

  const incrementValue = Number(incrementAmount) || 0;

  return (
    <div>
      <div className={styles.row}>
        <Button
          aria-label="Decrement value"
          onClick={() => dispatch(decrement())}
        >
          -
        </Button>
        <span className={styles.value}>{count}</span>
        <Button
          aria-label="Increment value"
          onClick={() => dispatch(increment())}
        >
          +
        </Button>
      </div>
      <div className={styles.row}>
        <Input
          aria-label="Set increment amount"
          value={incrementAmount}
          onChange={(e) => setIncrementAmount(e.target.value)}
        />
        <Button
          onClick={() => dispatch(incrementByAmount(incrementValue))}
        >
          Add Amount
        </Button>
        <Button
          onClick={() => dispatch(incrementAsync(incrementValue))}
        >
          Add Async
        </Button>
        <Button
          onClick={() => dispatch(incrementIfOdd(incrementValue))}
        >
          Add If Odd
        </Button>
      </div>
    </div>
  );
}
